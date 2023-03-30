#encoding: utf-8

module Deepspace
  class Damage
	@@NO_USE = -1
    private def initialize(nw , ns, w)
		@nWeapons = w
		@nShields = s
		@weapons = w
    end

    def self.newNumericWeapons(w, s)
		new(w,s,nil)	
    end

    #se iniciliza con una lista de WeaponType
    def self.newSpecificWeapons(w, s)
		new(@@NO_USE,s,w)
    end

    def self.newCopy(d)
		new(d.nWeapons,d.nShields,d.weapons)
    end

    def getUIversion
		return WeaponToUI.new(self)
    end

    private
    def arrayContainsType(w, t)
		encontrado = false
		pos = -1
		i = 0
		while i < w.length && !encotrado
			if (w.at(i).type == t)
				encotrado = true
				pos = i
			end
			i += 1
		end 
		return pos
    end

    public
    def adjust(w, s)
		
    end

    def discardWeapon(w)
		if (@nWeapons == @@NO_USE)
			if (@weapons.length != 0)
				@weapons.delete_if{ |a| a == w.type}
			end 
		else 
			if(@nWeapons>0) 
				@nWeapons -= 1
			end
		end 
    end

    def discardShieldBooster
		if(@nShields > 0) @nShields -= 1 
		end 
    end

    def hasNoEffect

    end

    def nShields
		@nShields
    end

    def nWeapons
		@nWeapons
    end

    def weapons
		@weapons
    end

    def to_s
      salida=""
      if(nWeapons==NO_USE)
        salida = "[Damage] -> weapons: " + @weapons.to_s +
          ", nShields: " + @nShields.to_s
      else
        salida = "[Damage] -> nWeapons: " + @nWeapons.to_s +
          ", nShields: " + @nShields.to_s

      return salida;
      end
    end

  end
end
