#encoding: utf-8

module Deepspace
  class EnemyStarShip
    def initialize(n, a, s, l, d)
      @name=n
      @ammoPower=a
      @shieldPower=s
      @loot=l
      @damage=d
    end

    def self.newCopy(e)
      new(e)
    end

    def getUIversion

    end

    public
    def fire

    end

    def ammoPower

    end

    def damage

    end

    def loot

    end

    def name

    end

    def shieldPower

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