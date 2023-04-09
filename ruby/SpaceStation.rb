#encoding: utf-8
require_relative "SpaceStationToUI"
require_relative "Hangar"
require_relative "Damage"
require_relative "Weapon"
require_relative "WeaponType"
require_relative "Loot"
require_relative "ShieldBooster"
require_relative "SuppliesPackage"
module Deepspace
  class SpaceStation
    @@MAXFUEL = 100
    @@SHIELDLOSSPERUNITSHOT = 0.1

    def initialize(n, supplies)
      @name=n
      @ammoPower=0
      @fuelUnits=0
      @shieldPower=0
      receiveSupplies(supplies)
      @nMedals=0
      @weapons=Array.new()
      @shieldBoosters=Array.new()
      @hangar=nil
      @pendingDamage=nil
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

    def getSpeed
      speed=0
      if(@@MAXFUEL!=0)
        speed=@fuelUnits.to_f/@@MAXFUEL
      else
        raise "Division entre 0, MAXFUEL no puede ser 0"
      end

      return speed
    end

    def getUIversion
      return SpaceStationToUI.new(self )
    end

    def mountShieldBooster(i)
      if(i>=0 && i<@hangar.shieldBoosters.length)
        if(@hangar != nil)
          s=@hangar.removeShieldBooster(i)
          if(s != nil)
            @shieldBoosters.push(s)
          end
        end
      end
    end

    def mountWeapon(i)
      if(i>=0 && i<@hangar.weapons.length)
        if(@hangar != nil)
          w=@hangar.removeWeapon(i)
          if(w != nil)
            @weapons.push(w)
          end
        end
      end
    end

    def move
      if ((@fuelUnits - getSpeed) > 0)
        @fuelUnits -= getSpeed
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
      @fuelUnits+=s.fuelUnits
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

    private
    def assignFuelValue(f)
      if(f < @@MAXFUEL)
        fuelUnits=f
      else
        fuelUnits=@@MAXFUEL
      end
    end

    def cleanPendingDamage
      if(pendingDamage!=nil)
        if(pendingDamage.hasNoEffect)
          @pendingDamage=nil
        end
      end
    end
    def discardShieldBooster(i)

    end

    def discardWeapon(i)

    end

    def setLoot(loot)

    end

    def receiveShot(shot)

    end

    def protection

    end

    def fire

    end
  end
end