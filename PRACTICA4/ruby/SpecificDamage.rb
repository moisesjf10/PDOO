require_relative 'SpecificDamageToUI'
require_relative "Damage"
module Deepspace
  class SpecificDamage < Damage
    def initialize(weaponlist,nshields)
      super(nshields)
      if(weaponlist!=nil)
        @weapons=Array.new(weaponlist)
      else
        @weapons=Array.new()
      end
    end

    def copy
      return self.class.new(@weapons,@nShields)
    end

    def weapons
      return @weapons
    end
    def arrayContainsType(weaponlist, weapontype)
      encontrado = false
      pos = -1
      i = 0
      while i < weaponlist.length && !encontrado
        if (weaponlist.at(i).type == weapontype)
          encontrado = true
          pos = i
        end
        i += 1
      end
      return pos
    end

    def adjust(w,s)
      aux = w.clone
      waux = Array.new()
      @weapons.each do |n|
        index = arrayContainsType(aux,n)
        if index != -1
          waux.push(n)
          aux.delete_at(index)
        end
      end
      return self.class.new(waux,super(s))
    end

    def discardWeapon(w)
      if (@weapons.length != 0)
        @weapons.delete_if{ |a| a == w.type}
      end
    end

    def hasNoEffect
      hasnoeffect=true
      if(@weapons!=nil)
        hasnoeffect=@weapons.length && super
      else
        hasnoeffect=super
      end
      return hasnoeffect
    end

    def getUIversion
      return SpecificDamageToUI.new(self)
    end

    def to_s
      getUIversion.to_s
    end
    private :arrayContainsType
  end
end
