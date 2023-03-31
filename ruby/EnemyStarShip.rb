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
      new(e.name, e.ammoPower, e.shieldPower, e.loot, e.damage)
    end

    def getUIversion
      return EnemyToUI.new(self)
    end

    public
    def fire
      return @ammoPower
    end

    def protection
      return shieldPower
    end

    def ammoPower
      return @ammoPower
    end

    def damage
      return damage
    end

    def loot
      return loot
    end

    def name
      return name
    end

    def shieldPower
      return shieldPower
    end

    def receiveShot(shot)
      resultado=ShotResult::RESIST
      if(shot>protection)
        resultado=ShotResult::DONOTRESIST
      end

      return resultado
    end

    def to_s
      getUIversion.to_s
    end

  end
end