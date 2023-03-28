#encoding: utf-8
#Esta clase representa el botín que se obtiene al vencer a una nave enemiga. 

module Deepspace
	class Loot
		def initialize(supplies, weapons, shields, hangars, medals)
			@nSupplies = supplies
			@nWeapons = weapons
			@nShields = shields
			@nHangars = hangars
			@nMedals = medals
		end
		
		def nSupplies
			@nSupplies
		end
		
		def nWeapons 
			@nWeapons 
		end
		
		def nShields
			@nShields
		end
		
		def nHangars
			@nHangars
		end
		
		def nMedals
			@nMedals
		end

		def to_s
			salida="[Loot] -> nSupplies: "+ nSupplies.to_s + ", nWeapons: "+ nWeapons.to_s + ", nShields: "+ nShields.to_s + ", nHangars: "+ nHangars.to_s + ", nMedals; "+ nMedals.to_s

			return salida
		end
	end
end
