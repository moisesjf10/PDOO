#encoding:utf-8
require_relative "WeaponType"
require_relative "Damage"
require_relative "ShieldBooster"
module Deepspace
	class Examen
		def self.principal
			weaponlist=[WeaponType::LASER,WeaponType::PLASMA,WeaponType::LASER,WeaponType::MISSILE]
			n_shields=4
			damage = Damage.newSpecificWeapons(weaponlist,n_shields)
			puts damage.to_s
			
			weaponlist=[WeaponType::MISSILE,WeaponType::LASER]
			shields=[ShieldBooster.new("escudo",2,2)]
			damageADJUST=damage.adjust(weaponlist,shields)
			puts damageADJUST.to_s
		end
	end
end

puts Deepspace::Examen.principal
