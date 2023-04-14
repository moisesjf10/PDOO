#encoding: utf-8

module Deepspace
  class GameUniverse
    @@WIN=10

    def initialize
      @currentStationIndex = -1
      @turns = 0
      @gameState = new GameStateController
      @dice = new Dice
      @currentStation = nil
      @currentEnemy = nil
      @spaceStations = Array.new()
    end

    def combat(station, enemy)

    end

    public
    def combat()

    end

    def discardHangar
      if(@gameState.state == GameState::INIT || @gameState.state == GameState::AFTERCOMBAT )
        @currentStation.discardHangar()
      end
    end

    def discardShieldBooster(i)
      if(@gameState.state == GameState::INIT || @gameState.state == GameState::AFTERCOMBAT )
        @currentStation.discardShieldBooster(i)
      end
    end

    def discardShieldBoosterInHangar(i)
      if(@gameState.state == GameState::INIT || @gameState.state == GameState::AFTERCOMBAT )
        @currentStation.discardShieldBoorterInHangar(i)
      end
    end

    def discardWeapon(i)
        if(@gameState.state == GameState::INIT || @gameState.state == GameState::AFTERCOMBAT )
          @currentStation.discardWeapon(i)
        end
    end

    def discardWeaponInHangar(i)
        if(@gameState.state == GameState::INIT || @gameState.state == GameState::AFTERCOMBAT )
          @currentStation.discardWeaponInHangar(i)
        end
    end

    def state
        @state
    end

    def getUIversion
        return (GameUniverseToUI.new(@currentStation,@currentEnemy))
    end

    def haveAWinner
      return (@currentStation.getNMedals() == WIN)
    end

    def init(names)
		
    end

    def mountShieldBooster(i)
        if(@gameState.state == GameState::INIT || @gameState.state == GameState::AFTERCOMBAT )
          @currentStation.mountShieldBooster(i)
        end
    end

    def mountWeapon(i)
        if(@gameState.state == GameState::INIT || @gameState.state == GameState::AFTERCOMBAT )
          @currentStation.mountWeapon(i)
        end
    end

    def nextTurn

    end

    def to_s
      getUIversion.to_s
    end
  end

end
