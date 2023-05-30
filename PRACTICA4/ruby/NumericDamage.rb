require_relative 'NumericDamageToUI'
require_relative "Damage"
module Deepspace
  class NumericDamage < Damage
    def initialize(nweapons,nshields)
      super(nshields)
      @nWeapons=nweapons
    end

    def copy
      return self.class.new(@nWeapons,@nShields)
    end

    def nWeapons
      return @nWeapons
    end

    def adjust(w,s)
      return self.class.new([@nWeapons,w.length].min,super(s))
    end

    def discardWeapon(w)
      if(@nWeapons>0)
        @nWeapons-=1
      end
    end

    def hasNoEffect
      return (@nWeapons + @nShields) == 0
    end

    def getUIversion
      return NumericDamageToUI.new(self)
    end
    def to_s
      getUIversion().to_s
    end
  end

end
