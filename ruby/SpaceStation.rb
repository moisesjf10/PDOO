#encoding: utf-8
require_relative 'SpaceStationToUI'
require_relative 'Hangar'
require_relative 'Damage'
require_relative 'Weapon'
require_relative 'WeaponType'
require_relative 'Loot'
require_relative 'ShieldBooster'
require_relative 'SuppliesPackage'
require_relative 'CardDealer'
module Deepspace
  class SpaceStation
    @@MAXFUEL = 100
    @@SHIELDLOSSPERUNITSHOT = 0.1

    def initialize(n, supplies)
      @name=n
      @ammoPower=0.0
      @fuelUnits=0.0
      @shieldPower=0.0
      @nMedals=0
      @weapons=Array.new()
      @shieldBoosters=Array.new()
      @hangar=nil
      @pendingDamage=nil
      receiveSupplies(supplies)
    end

    def self.copy(station)
      new(station.name, SuppliesPackage.new(station.ammoPower, station.fuelUnits, station.shieldPower))
      @nMedals = station.nMedals

      if station.weapons != nil
        @weapons = station.weapons.clone
      end
      if station.shieldBoosters != nil
        @shieldBoosters=station.shieldBoosters.clone
      end
      if station.hangar != nil
        @hangar=station.hangar.clone
      end
      if station.pendingDamage != nil
        @pendingDamage=station.pendingDamage.clone
      end

    end

    public
    def cleanUpMountedItems
      @weapons=@weapons.select { |w| w.uses > 0 }
      @shieldBoosters=@shieldBoosters.select{|s| s.uses > 0}
    end

    def discardHangar
      @hangar=nil
    end

    def discardShieldBoosterInHangar(i)
      if(@hangar != nil)
        @hangar.removeShieldBooster(i)
      end
    end

    def discardWeaponInHangar(i)
      if(@hangar != nil)
        @hangar.removeWeapon(i)
      end
    end

    def ammoPower
      @ammoPower
    end

    def fuelUnits
      @fuelUnits
    end

    def hangar
      @hangar
    end

    def name
      @name
    end

    def nMedals
      @nMedals
    end

    def pendingDamage
      @pendingDamage
    end

    def shieldBoosters
      @shieldBoosters
    end

    def weapons
      @weapons
    end

    def shieldPower
      @shieldPower
    end

    def speed
      s=0
      if(@@MAXFUEL!=0)
        s=@fuelUnits.to_f/@@MAXFUEL
      else
        raise "Division entre 0, MAXFUEL no puede ser 0"
      end

      return s
    end

    def getUIversion
      return SpaceStationToUI.new(self )
    end

    def mountShieldBooster(i)
      if(@hangar != nil)
        if(i>=0 && i<@hangar.shieldBoosters.length)
          s=@hangar.removeShieldBooster(i)
          if(s != nil)
            @shieldBoosters.push(s)
          end
        end
      end
    end

    def mountWeapon(i)
      if(@hangar != nil)
        if(i>=0 && i<@hangar.weapons.length)
          w=@hangar.removeWeapon(i)
          if(w != nil)
            @weapons.push(w)
          end
        end
      end
    end

    def move
      @fuelUnits-=@fuelUnits*speed
      if (fuelUnits<=0)
        @fuelUnits = 0
      end
    end

    def receiveHangar(h)
      if(@hangar==nil)
        @hangar=h
      end
    end

    def receiveWeapon(w)
      resultado=false
      if(@hangar != nil)
        resultado=@hangar.addWeapon(w)
      end

      return resultado
    end

    def receiveShieldBooster(s)
      resultado=false
      if(@hangar != nil)
        resultado=@hangar.addShieldBooster(s)
      end

      return resultado
    end

    def receiveSupplies(s)
      @ammoPower+=s.ammoPower
      assignFuelValue(@fuelUnits + s.fuelUnits)
      @shieldPower+=s.shieldPower
    end

    def setPendingDamage(d)
      if (d != nil)
        @pendingDamage=d.adjust(@weapons, @shieldBoosters)
      end
    end

    def validState
      resultado=false
      if(@pendingDamage==nil)
        resultado=true
      elsif(@pendingDamage.hasNoEffect)
        resultado=true
      end

      return resultado
    end

    def to_s
      getUIversion.to_s
    end
    def discardShieldBooster(i)
      size=@shieldBoosters.length
      if(i>=0 && i<size)
        s=@shieldBoosters.delete_at(i)

        if(@pendingDamage!=nil)
          @pendingDamage.discardShieldBooster
          cleanPendingDamage
        end
      end
    end

    def discardWeapon(i)
      size=@weapons.length
      if(i>=0 && i<size)
        w=@weapons.delete_at(i)

        if(@pendingDamage!=nil)
          @pendingDamage.discardWeapon(w)
          cleanPendingDamage
        end
      end
    end

    def setLoot(loot)
      dealer=CardDealer.instance
      h=loot.nHangars

      if(h>0)
        hangar=dealer.nextHangar
        receiveHangar(hangar)
      end

      elements=loot.nSupplies

      elements.times do
        sup=dealer.nextSuppliesPackage
        receiveSupplies(sup)
      end

      elements=loot.nWeapons

      elements.times do
        weap=dealer.nextWeapon
        receiveWeapon(weap)
      end

      elements=loot.nShields

      elements.times do
        sh=dealer.nextShieldBooster
        receiveShieldBooster(sh)
      end

      @nMedals+=loot.nMedals

      transformation=Transformation::NOTRANSFORM
      if(loot.getEfficient)
        transformation=Transformation::GETEFFICIENT
      elsif(loot.spaceCity)
        transformation=Transformation::SPACECITY
      end

      return transformation
    end

    def receiveShot(shot)
      shotresult=ShotResult::DONOTRESIST
      myProtection=protection
      if(myProtection>=shot)
        @shieldPower-=@@SHIELDLOSSPERUNITSHOT*shot
        if(@shieldPower<0.0)
          @shieldPower=0.0
        end
        shotresult=ShotResult::RESIST
      else
        @shieldPower=0.0
      end

      return shotresult
    end

    def protection
      factor=1
      @shieldBoosters.each do |s|
        factor*=s.useIt
      end
      return @shieldPower*factor
    end

    def fire
      factor=1.0
      @weapons.each do |w|
        factor*=w.useIt
      end
      return @ammoPower*factor
    end
    private
    def assignFuelValue(f)
      if(f <= @@MAXFUEL)
        @fuelUnits=f
      else
        @fuelUnits=@@MAXFUEL
        @fuelUnits=@@MAXFUEL
      end
    end

    def cleanPendingDamage
      if(pendingDamage!=nil)
        if(pendingDamage.hasNoEffect)
          @pendingDamage=nil
        end
      end
    end

  end
end
