#encoding: utf-8

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
      @weapons=[]
      @shieldBoosters=[]
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

    def discardShieldBooster(i)

    end

    def discardShieldBoosterInHangar(i)

    end

    def discardWeapon(i)

    end

    def discardWeaponInHangar(i)

    end

    def fire

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
      return fuelUnits/@@MAXFUEL
    end

    def getUIversion
      return SpaceStationToUI.new(self )
    end

    def mountShieldBooster(i)

    end

    def mountWeapon(i)

    end

    def move

    end

    def protection

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
      if(@shieldBoosters != nil)
        resultado=@hangar.addShieldBooster(s)
      end

      return resultado
    end

    def receiveShot(shot)

    end

    def receiveSupplies(s)
      @ammoPower+=s.ammoPower
      @fuelUnits+=s.fuelUnits
      @shieldPower+=s.shieldPower
    end

    def setLoot(loot)

    end

    def setPendingDamage(d)
      
    end

    def validState

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
      if(pendingDamage.hasNoEffect)
        pendingDamage=nil
      end
    end


  end
end