#encoding: utf-8
require_relative 'DamageToUI'
require_relative 'WeaponType'
require_relative 'Weapon'

module Deepspace
  class Damage
      def initialize(nshields)
        @nShields = nshields
      end

      def nShields
        @nShields
      end
      def discardShieldBooster
        if(@nShields > 0)
          @nShields -= 1
        end
      end
      def adjust(s)
        return [s.length, @nShields].min
      end

      def hasNoEffect
        return @nShields==0
      end

      def getUIversion
        return DamageToUI.new(self)
      end
      def to_s
        getUIversion().to_s
      end
  end
end
