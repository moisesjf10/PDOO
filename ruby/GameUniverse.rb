#encoding: utf-8

module Deepspace
  class GameUniverse
    @@WIN=10

    def initialize

    end

    def combat(station, enemy)

    end

    public
    def discardHangar

    end

    def discardShieldBooster(i)

    end

    def discardShieldBoosterInHangar(i)

    end

    def discardWeapon(i)

    end

    def discardWeaponInHangar(i)

    end

    def getState

    end

    def getUIversion

    end

    def haveAWinner

    end

    def init(names)

    end

    def mountShieldBooster(i)

    end

    def mountWeapon(i)

    end

    def nextTurn

    end

    def to_s
      salida="[GameUniverse] -> WIN: "+ @@WIN +
        ", currentStationIndex: "+ currentStationIndex.to_s +
        ", gameState: "+ gameState.to_s +
        ", currentEnemy: "+ currentEnemy.to_s+
        ", currentStation: "+ currentStation.to_s +
        ", spaceStations: "+ spaceStations.to_s

      return salida
    end
  end

end