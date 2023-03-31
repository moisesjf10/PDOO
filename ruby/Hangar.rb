#encoding: utf-8

module Deepspace
  class Hangar
    def initialize(capacity)
      @maxElements=capacity
      @shieldBoosters=Array.new()
      @weapons=Array.new()
    end

    def self.newCopy(h)
		new(h.maxElements)
		h.shieldBoosters.each{addShieldBooster(h.shieldBoosters.at(i))}
		h.weapons.each{addWeapon(h.weapons.at(i))}
    end

    def getUIversion
		return HangarToUI.new(self)
    end

    private
    def spaceAvailable
		(@shieldBoosters.length + @weapons.length) < @maxElements
    end

    public
    def addWeapon(w)
		insertar = true;
		if(spaceAvailable)
      @weapons.push(w)
    else
      insertar = false
    end

		return insertar
    end

    def addShieldBooster(s)
		insertar = true
		if(spaceAvailable)
      @shieldBoosters.push(s)
    else
      insertar = false
    end
		return insertar
    end

    def maxElements
		@maxElements
    end

    def shieldBoosters
		@shieldBoosters
    end

    def weapons
		@weapons
    end

    def removeShieldBooster(s)
		shield = nil
		if (0<=w && w<@weapons.length) 
			shield = @shieldBoosters.at(s)
			@shieldBoosters.delete_at(s)
		end
		return weapon
    end

    def removeWeapon(w)
		weapon = nil
		if (0<=w && w<@weapons.length) 
			weapon = @weapons.at(w)
			@weapons.delete_at(w)
		end
		return weapon
    end

    def to_s
      getUIversion.to_s
    end

  end

end
