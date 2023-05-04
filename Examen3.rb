
require_relative "SpaceStation"
require_relative 'SuppliesPackage'
require_relative 'Hangar'
require_relative 'Weapon'
require_relative 'WeaponType'
require_relative 'ShieldBooster'
require_relative 'Damage'

module Test 
	class Examen3
		def principal
		
			#Pregunta 2
		
			paquete = Deepspace::SuppliesPackage.new(2,5,3)
			estacion = Deepspace::SpaceStation.new("Manuel",paquete)
			puts "(2.a) " + estacion.to_s 
			
			#2.b
			
			hangar = Deepspace::Hangar.new(3)
			estacion.receiveHangar(hangar)
			puts "(2.b) " + estacion.to_s 
			
			#2.c
			arma = Deepspace::Weapon.new("Espada", Deepspace::WeaponType::LASER, 1)
			estacion.receiveWeapon(arma)
			puts "(2.c) " + estacion.to_s 
			
			#2.d
			estacion.receiveWeapon(arma)
			estacion.receiveWeapon(arma)
			estacion.receiveWeapon(arma)
			puts "(2.d) " + estacion.to_s 
			
			#2.e
			estacion.mountWeapon(0)
			estacion.mountWeapon(0)
			puts "(2.e) " + estacion.to_s 
			
			#2.f
			escudo = Deepspace::ShieldBooster.new("Escudo",1.5, 2)
			estacion.receiveShieldBooster(escudo)
			puts "(2.f) " + estacion.to_s
			
			#2.g
			estacion.receiveShieldBooster(escudo)
			estacion.receiveShieldBooster(escudo)
			puts "(2.g) " + estacion.to_s
			
			#2.h
			estacion.mountShieldBooster(1)
			puts "(2.h) " + estacion.to_s
			
			#Pregunta 3
			
			wl= Array.new()
			wl.push(Deepspace::WeaponType::PLASMA)
			wl.push(Deepspace::WeaponType::LASER)
			wl.push(Deepspace::WeaponType::MISSILE)
			
			
			d = Deepspace::Damage.newSpecificWeapons(wl,3)
			
			#3.b
			puts "(3.b) " + d.to_s
			
			#3.c
			
			arma2 = Deepspace::Weapon.new("Espada", Deepspace::WeaponType::LASER, 1)
			arma3 = Deepspace::Weapon.new("Espada", Deepspace::WeaponType::MISSILE, 1)
			
			w = Array.new()
			s = Array.new()
			
			w.push(arma2)
			w.push(arma3)
			w.push(arma2)
			
			s.push(escudo)
			s.push(escudo)
			
			
			
			prueba = d.adjust(w,s)
			
			puts "(3.c) " + prueba.to_s
			
			
			
			
			
		end
	end
end	

e = Test::Examen3.new()

e.principal

