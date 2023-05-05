#encoding:utf-8

require_relative 'SpaceStation'
require_relative 'SpaceCityToUI'

module Deepspace
  class SpaceCity < SpaceStation
    def initialize(base_, rest_)
      super(base_)
      @base=base_
      @collaborators=rest_
    end

    def collaborators
      return @collaborators
    end

    def fire
      fire=super
      @collaborators.each do |station|
        fire += station.fire
      end

      return fire
    end

    def protection
      protection=super
      @collaborators.each do |station|
        protection+=station.protection
      end
      return protection
    end

    def setLoot(loot)
      super(loot)
      return Transformation::NOTRANSFORM
    end

    def to_s
      getUIversion().to_s
    end
    def getUIversion
      return SpaceCityToUI.new(self)
    end
  end
end