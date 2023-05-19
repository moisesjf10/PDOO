#encoding: utf-8
require_relative 'GameUniverseToUI'
require_relative 'GameStateController'
require_relative 'Dice'
require_relative 'Loot'
require_relative 'CombatResult'
require_relative 'GameCharacter'
require_relative 'ShotResult'
require_relative 'SpaceStation'
require_relative 'CardDealer'
require_relative 'EnemyStarShip'
require_relative 'Transformation'
require_relative 'PowerEfficientSpaceStation'
require_relative 'BetaPowerEfficientSpaceStation'
require_relative 'SpaceCity'

module Deepspace
  class GameUniverse
    @@WIN=10

    def initialize
      @currentStationIndex = -1
      @turns = 0
      @gameState = GameStateController.new
      @dice = Dice.new
      @currentStation = nil
      @currentEnemy = nil
      @spaceStations = Array.new()
      @haveSpaceCity = false
    end

    def makeStationEfficient
      if (@dice.extraEfficiency)
        @currentStation = BetaPowerEfficientSpaceStation.new(@currentStation)
      else
        @currentStation = PowerEfficientSpaceStation.new(@currentStation)
      end
      @spaceStations[@currentStationIndex] = @currentStation
    end

    def createSpaceCity
      if !@haveSpaceCity
        rest = Array.new()
        SpaceStation.each do |n|
          if n != @currentStation
            rest.push(n)
          end
        end
        @currentStation = new SpaceCity(@currentStation,rest)
        @spaceStations[@currentStationIndex] = @currentStation
        @haveSpaceCity = true
      end
    end


    def combatGo(station, enemy)
      enemyWins = false
      ch = @dice.firstShot
      combatResult = CombatResult::ENEMYWINS

      if ch == GameCharacter::ENEMYSTARSHIP
        fire = enemy.fire
        result = station.receiveShot(fire)

        if result == ShotResult::RESIST
          fire = station.fire
          result = enemy.receiveShot(fire)
          enemyWins = (result == ShotResult::RESIST)
        else
          enemyWins = true
        end
      else
        fire = station.fire
        result = enemy.receiveShot(fire)
        enemyWins = (result == ShotResult::RESIST)
      end

      if enemyWins
        s = station.speed
        moves = @dice.spaceStationMoves(s)

        if !moves
          damage = enemy.damage
          station.setPendingDamage(damage)
          combatResult = CombatResult::ENEMYWINS
        else
          station.move
          combatResult = CombatResult::STATIONESCAPES
        end
      else
        aloot = enemy.loot
        t = station.setLoot(aloot)
        combatResult=CombatResult::STATIONWINS
        if t == Transformation::GETEFFICIENT
          makeStationEfficient
          combatResult = CombatResult::STATIONWINSANDCONVERTS
        elsif t == Transformation::SPACECITY
          createSpaceCity
          combatResult = CombatResult::STATIONWINSANDCONVERTS
        end
      end
      @gameState.next(@turns,@spaceStations.length)
      return combatResult
    end

    public
    def combat()
      result = CombatResult::NOCOMBAT
      if state == GameState::BEFORECOMBAT || state == GameState::INIT
        result = combatGo(@currentStation,@currentEnemy)
      end
      return result
    end

    def discardHangar
      if(state == GameState::INIT || state == GameState::AFTERCOMBAT )
        @currentStation.discardHangar()
      end
    end

    def discardShieldBooster(i)
      if(state == GameState::INIT || state == GameState::AFTERCOMBAT )
        @currentStation.discardShieldBooster(i)
      end
    end

    def discardShieldBoosterInHangar(i)
      if(state == GameState::INIT || state == GameState::AFTERCOMBAT )
        @currentStation.discardShieldBoorterInHangar(i)
      end
    end

    def discardWeapon(i)
        if(state == GameState::INIT || state == GameState::AFTERCOMBAT )
          @currentStation.discardWeapon(i)
        end
    end

    def discardWeaponInHangar(i)
        if(state == GameState::INIT || state == GameState::AFTERCOMBAT )
          @currentStation.discardWeaponInHangar(i)
        end
    end

    def state
      @gameState.state
    end

    def getUIversion
        return (GameUniverseToUI.new(@currentStation,@currentEnemy))
    end

    def haveAWinner
      return (@currentStation.nMedals >= @@WIN)
    end

    def init(names)
        if(state == GameState::CANNOTPLAY)
          dealer = CardDealer.instance
          names.each do |n|
              supplies = dealer.nextSuppliesPackage()
              station = SpaceStation.new(n,supplies)
              @spaceStations.push(station)

              nh = @dice.initWithNHangars
              nw = @dice.initWithNWeapons
              ns = @dice.initWithNShields

              lo = Loot.new(0,nw,ns,nh,0)
              station.setLoot(lo)
          end
          @currentStationIndex = @dice.whoStarts(names.length)
          @currentStation = @spaceStations[@currentStationIndex]
          @currentEnemy = dealer.nextEnemy

          @gameState.next(@turns,@spaceStations.length)
        end
    end

    def mountShieldBooster(i)
        if(state == GameState::INIT || state == GameState::AFTERCOMBAT )
          @currentStation.mountShieldBooster(i)
        end
    end

    def mountWeapon(i)
        if(state == GameState::INIT || state == GameState::AFTERCOMBAT )
          @currentStation.mountWeapon(i)
        end
    end

    def nextTurn
      turn = false
      if state == GameState::AFTERCOMBAT
        stationState = @currentStation.validState
        if stationState
          @currentStationIndex = (@currentStationIndex+1)%@spaceStations.length
          @turns+=1
          @currentStation = @spaceStations[@currentStationIndex]
          @currentStation.cleanUpMountedItems
          dealer = CardDealer.instance
          @currentEnemy = dealer.nextEnemy
          @gameState.next(@turns,@spaceStations.length)
          turn = true
        end
      end
      return turn
    end

    def to_s
      getUIversion.to_s
    end
  end

end