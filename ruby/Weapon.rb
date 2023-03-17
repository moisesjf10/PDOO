#encoding: utf-8
#Esta clase representa a las armas de las que puede disponer una estación 
#espacial para potenciar su energía al disparar
require_relative "WeaponType" 

module Deepspace
	class Weapon
		@DEFAULT_POWER = 1.0
		@MIN_USES = 0
		
		def initialize(name, type, uses)
			@name = name
			@type = type
			@uses = uses
		end
		
		def self.newCopy(weapon)
			new(weapon.name, weapon.type, weapon.uses)
		end
		
		def name
			@name
		end
		
		def type
			@type
		end
		
		def uses
			@uses
		end
		
		def power
			type.power
		end
		
		def self.min_uses
			@MIN_USES
		end
			
		def self.default_power
			@DEFAULT_POWER
		end
		
		def useIt
			aux=0
			if uses > self.class.min_uses then
				@uses-=1
				aux=power
			else
				aux= self.class.default_power
			end
			
			return aux
		end
	end
end
