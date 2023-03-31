
module Deepspace
	class ShieldBooster
		@DEFAULT_BOOST = 1.0
		@MIN_USES = 0
		
		def initialize (name,boost,uses)
			@name = name
			@boost = boost
			@uses = uses
		end 
		
		def self.newCopy(o)
			new(o.name,o.boost,o.uses)
		end
		
		def name
			@name
		end 
		
		def boost
			@boost
		end 
		
		def uses
			@uses
		end 
		
		def self.min_uses
			@MIN_USES
		end
			
		def self.default_boost
			@DEFAULT_BOOST
		end
		
		def useIt
			aux = 0
			if @uses > self.class.min_uses then
				@uses -= 1
				aux = boost
			else 
				aux = self.class.default_boost
			end 
			return aux
		end

		def getUIversion
			return ShieldToUI.new(self)
		end

		def to_s
			getUIversion.to_s
		end
	end
end
