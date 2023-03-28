#encoding: utf-8

module Deepspace
  class Hangar
    def initialize(capacity)
      @maxElements=capacity
    end

    def self.newCopy(h)

    end

    def getUIversion
    end

    private
    def spaceAvailable

    end

    public
    def addWeapon(w)

    end

    def addShieldBooster(s)

    end

    def getMaxElements

    end

    def getShieldBooster

    end

    def getWeapons

    end

    def removeShieldBooster(i)

    end

    def removeWeapon

    end

    def to_s
      salida="[Hangar] -> maxElements: "+ maxElements.to_s +
        ", shieldBoosters: "+ shieldBoosters.to_s +
        ", weapons: "+ weapons.to_s

      return salida
    end

  end

end