#!/usr/bin/env ruby

require_relative 'CombatResult'
require_relative 'Dice'
require_relative 'GameCharacter'
require_relative 'Loot'
require_relative 'ShieldBooster'
require_relative 'ShotResult'
require_relative 'SuppliesPackage'
require_relative 'Weapon'
require_relative 'WeaponType'

	class TestP1
		def main
		
			puts "Probando todos los métodos get:\n"
			
			puts "Enum Combat Result:\n"
			
			puts Deepspace::CombatResult::ENEMYWINS
			puts Deepspace::CombatResult::NOCOMBAT
			puts Deepspace::CombatResult::STATIONESCAPES
			puts Deepspace::CombatResult::STATIONWINS			
			puts "Enum Game Character:\n"
			
			puts Deepspace::GameCharacter::SPACESTATION
			puts Deepspace::GameCharacter::ENEMYSTARSHIP
			
			puts "Enum Weapon Type: \n"

			puts Deepspace::WeaponType::LASER.power
			puts Deepspace::WeaponType::MISSILE.power
			puts Deepspace::WeaponType::PLASMA.power
			
			puts "Enum Shot Result: \n" 
			
			puts Deepspace::ShotResult::DONOTRESIST
			puts Deepspace::ShotResult::RESIST
			
			puts "Probando las clases: \n"
			
			puts "Clase Loot: \n"
			loot = Deepspace::Loot.new(1,2,3,4,5)
			
			puts loot.nSupplies
			puts loot.nWeapons
			puts loot.nShields
			puts loot.nHangars
			puts loot.nMedals
			
			puts "Clase SuppliesPackage: \n"
			p = Deepspace::SuppliesPackage.new(1.43,2.34,5.76)
			
			puts p.ammoPower
			puts p.fuelUnits
			puts p.shieldPower
			
			q = Deepspace::SuppliesPackage.newCopy(p)
			
			puts q.ammoPower
			puts q.fuelUnits
			puts q.shieldPower
			
			puts "Clase ShieldBooster: \n"
			
			s = Deepspace::ShieldBooster.new("nave",3.26,26)
			puts s.boost
			puts s.uses
			puts s.useIt
			
			d = Deepspace::ShieldBooster.newCopy(s)
			
			puts d.boost
			puts d.uses
			puts d.useIt
			
			puts "Clase Weapon: \n"
			
			w = Deepspace::Weapon.new("arma",Deepspace::WeaponType::LASER,40)
			
			puts w.type
			puts w.uses
			puts w.power
			puts w.useIt
			
			z = Deepspace::Weapon.newCopy(w)
			
			puts z.type
			puts z.uses
			puts z.power
			puts z.useIt
			
			puts "Clase Dice:\n"
			
			dice = Deepspace::Dice.new
			
			puts "init with Hangars:\n"
			
			reps = 10000.00
			
			numeros = [] 
			
			for i in(0..9)
				numeros.insert(i,0)
			end
			
			for i in(0..reps)
				if (dice.initWithNHangars == 0)
					numeros[0] += 1
				else
					numeros[1] += 1
				end
			end
			
			puts "unos: " + (numeros[1]/reps).to_s
			puts "ceros: " + (numeros[0]/reps).to_s
			
			puts "\n"
			
			numeros[0] = 0
			numeros[1] = 0 
			puts "Compruebo el init with NWeapons:"
			
			for i in (0..reps)
				valor = dice.initWithNWeapons
				if (valor == 1)
					numeros[1] += 1
				elsif (valor == 2)
					numeros[2] += 1
				elsif (valor == 3) 
					numeros[3] += 1
				end 
			end
			
			puts "unos:" + (numeros[1]/reps).to_s
			puts "dos:" + (numeros[2]/reps).to_s
			puts "tres:" + (numeros[3]/reps).to_s
			
			puts "\n"
			
			numeros[1] = 0
			numeros[2] = 0
			numeros[3] = 0
			
			puts " init with nShields:\n"
			
			for i in(0..reps)
				if (dice.initWithNShields == 0)
					numeros[0] += 1
				else
					numeros[1] += 1
				end
			end
			
			puts "unos: " + (numeros[1]/reps).to_s
			puts "ceros: " + (numeros[0]/reps).to_s
			
			numeros[0] = 0
			numeros[1] = 0 
			
			puts "Compruebo el whostars:\n"
			players = 10
			for i in(0..reps)
				numeros[dice.whoStarts(players)] += 1
			end 
			
			for i in(0..players-1)
				puts "Probabilidad de " + i.to_s + " : " + (numeros[i]/reps).to_s + "\n"
			end 
			
			puts "Compruebo el firstShot:\n"
			
			estacion = 0;
            enemigo = 0;
            
            for i in(0..reps)
				if (dice.firstShot == Deepspace::GameCharacter::SPACESTATION)
					estacion += 1
				else
					enemigo += 1
				end
			end
			
			puts "Estacion: " + (estacion/reps).to_s
			puts "Enemigo: " + (enemigo/reps).to_s
			
			numeros[0] = 0
			numeros[1] = 0
			numeros[2] = 0
			
			speed = 0.4
			
			puts "Compruebo el space Station Moves:\n"
			
			for i in(0..reps)
				if (dice.spaceStationMoves(speed) == false)
					numeros[0] += 1
				else
					numeros[1] += 1
				end
			end
			
			puts "unos: " + (numeros[1]/reps).to_s
			puts "ceros: " + (numeros[0]/reps).to_s
			
			puts "\n"
			
		end 
	end 

prueba = TestP1.new()

puts prueba.main









