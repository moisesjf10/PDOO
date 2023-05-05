
module Deepspace
  class BetaPowerEfficientSpaceStation < PowerEfficientSpaceStation
    @@EXTRAEFFICIENCY = 1.20

    def initialize(station)
      super(station)
      @dice=Dice.new
    end

    def fire
      fire = 0
      if(@dice.extraEfficiency)
        fire = super()*@@EXTRAEFFICIENCY
      else
        fire = super()
      end
      return fire
    end

    def getUIversion
      return BetaPowerEfficientSpaceStationToUI.new(self)
    end

    def to_s
      return getUIversion.to_s
    end
  end
end