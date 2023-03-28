#encoding: utf-8

module Deepspace
  class EnemyStarShip
    def initialize

    end

    def self.newCopy(e)

    end

    def getUIversion

    end

    public
    def fire

    end

    def getAmmoPower

    end

    def getDamage

    end

    def getLoot

    end

    def getName

    end

    def getShieldPower

    end

    def protection

    end

    def receiveShot(shot)

    end

    def to_s
      salida="[EnemyStarShip] -> nWeapons: "+ name.to_s +
        ", ammoPower: "+ ammoPower.to_s +
        ", shieldPower: "+ shieldPower.to_s +
        ", loot: "+ loot.to_s +
        ", damage: "+ damage.to_s

      return salida
    end

  end
end