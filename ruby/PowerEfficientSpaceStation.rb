

module Deepspace
  class PowerEfficientSpaceStation < SpaceStation
    @@EFFICIENCYFACTOR = 1.1

    def initialize(station)
      copy(station)
    end

    def fire
      return super * @@EFFICIENCYFACTOR
    end

    def protection
      return super * @@EFFICIENCYFACTOR
    end

    def setLoot(loot)
      t = super(loot)
      if t==Deepspace::Transformation::SPACECITY
        t=Deepspace::Transformation::NOTRANSFORM
      end
      return t
    end

    def getUIversion
      return PowerEfficientSpaceStationToUI.new(self)

    end

    def to_s
      return getUIversion.to_s
    end
  end
end