#encoding: utf-8

module Deepspace
  class Damage
    private def initialize(nw , ns, w)

    end

    def self.newNumericWeapons(w, s)

    end

    #se iniciliza con una lista de WeaponType
    def self.newSpecificWeapons(w, s)

    end

    def self.newCopy(d)

    end

    def getUIversion

    end

    private
    def arrayContainsType(w, t)

    end

    public
    def adjust(w, s)

    end

    def discardWeapon(w)

    end

    def discardShieldBooster

    end

    def hasNoEffect

    end

    def getNShields

    end

    def getNWeapons

    end

    def getWeapons

    end

    def to_s
      salida=""
      if(nWeapons==NO_USE)
        salida = "[Damage] -> weapons: " + weapons.to_s +
          ", nShields: " + nShields.to_s
      else
        salida = "[Damage] -> nWeapons: " + nWeapons.to_s +
          ", nShields: " + nShields.to_s

      return salida;
    end


  end
end