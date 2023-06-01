module Deepspace
  class FaultySpecificDamage < Damage
    def initialize(weaponlist,nshields)
      super(nshields)
      if(weaponlist!=nil)
        @weapons=Array.new(weaponlist)
      else
        @weapons=Array.new()
      end
      @dice=Dice.new()
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
      
      if(@dice.loseWeapon)
      	index=arrayContainsType(waux,@dice.weaponLost)
      	if index != -1
          waux.delete_at(index)
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




En Dice
@LOSEWEAPONPROB=0.2


def loseWeapon
	lose=false
	if @generator.rand()<=@LOSEWEAPONPROB
		lose=true
	end
	return lose
end

def weaponLost()
	w=WeaponType::LASER
	p=@generator.rand(0..3)
	if p==0
		w=WeaponType::MISSILE
	elsif p==1
		w=WeaponType::PLASMA
	end
	
	return w
end


























