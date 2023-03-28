#encoding: utf-8
#Esta clase representa a un paquete de suministros para una estación espacial.
module Deepspace
	class SuppliesPackage
		def initialize (ammopower, fuelunits, shieldpower)
			@ammoPower = ammopower
			@fuelUnits = fuelunits
			@shieldPower = shieldpower
		end
		
		def self.newCopy(package)
			new(package.ammoPower, package.fuelUnits, package.shieldPower)
		end
		
		def ammoPower
			@ammoPower
		end
		
		def fuelUnits
			@fuelUnits
		end
		
		def shieldPower
			@shieldPower
		end

		def to_s
			salida="[SuppliesPackage] -> ammoPower: "+ ammoPower.to_s + ", fuelUnits: "+ fuelUnits.to_s + ", shieldPower: "+ shieldPower.to_s
			return salida
		end
	end
end
	

