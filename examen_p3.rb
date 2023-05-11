#encoding: utf-8
require_relative 'Damage'
require_relative 'WeaponType'
require_relative 'ShieldBooster'
module P3
	class ExamenP3
		def self.principal
			numericdamage=Deepspace::Damage.newNumericWeapons(2,3)
			specificdamage=Deepspace::Damage.newSpecificWeapons([Deepspace::WeaponType::LASER,Deepspace::WeaponType::LASER,Deepspace::WeaponType::PLASMA],0)
			
			arrayweapons=[Deepspace::WeaponType::PLASMA,Deepspace::WeaponType::PLASMA,Deepspace::WeaponType::LASER,Deepspace::WeaponType::LASER,Deepspace::WeaponType::LASER]
			arrayshields=[Deepspace::ShieldBooster.new("a",2.0,2),Deepspace::ShieldBooster.new("b",2.0,2)]
			numericdamageADJUST=numericdamage.adjust(arrayweapons,arrayshields)		
			specificdamageADJUST=specificdamage.adjust(arrayweapons,arrayshields)
			
			puts "Primer adjust"
			puts numericdamageADJUST.to_s
			puts specificdamageADJUST.to_s
			
			arrayweapons=[Deepspace::WeaponType::PLASMA,Deepspace::WeaponType::PLASMA,Deepspace::WeaponType::LASER,Deepspace::WeaponType::LASER,Deepspace::WeaponType::MISSILE,Deepspace::WeaponType::MISSILE]
			
			numericdamageADJUST=numericdamage.adjust(arrayweapons,arrayshields)		
			specificdamageADJUST=specificdamage.adjust(arrayweapons,arrayshields)
			
			puts "Segundo adjust"
			puts numericdamageADJUST.to_s
			puts specificdamageADJUST.to_s		
		end
	
	
	
	end
end

puts P3::ExamenP3.principal
