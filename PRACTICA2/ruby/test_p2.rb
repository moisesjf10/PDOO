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
require_relative 'Damage'
require_relative 'EnemyStarShip'
require_relative 'SpaceStation'
require_relative 'Hangar'
require_relative 'GameUniverse'
class TestP2
  def main
    puts "Probando clase Damage"
    damage = Deepspace::Damage.newNumericWeapons(15, 20)
    puts damage.nShields
    puts damage.nWeapons
    puts damage.weapons
    puts damage.to_s

    arma1 = Deepspace::Weapon.new("laser", Deepspace::WeaponType::LASER, 8)
    arma2 = Deepspace::Weapon.new("misil", Deepspace::WeaponType::MISSILE, 10)
    arma3 = Deepspace::Weapon.new("plasma", Deepspace::WeaponType::PLASMA, 15)

    armas = Array.new()
    armas.push(arma1)
    armas.push(arma2)
    armas.push(arma3)

    arma = Array.new()
    arma.push(arma1.type)
    arma.push(arma2.type)

    damage2 = Deepspace::Damage.newSpecificWeapons(arma, 10)
    escudo = Deepspace::ShieldBooster.new("name", 2, 2)
    escudos = Array.new()
    escudos.push(escudo)

    puts damage2.nShields
    puts damage2.nWeapons
    puts damage2.weapons

    damage3 = Deepspace::Damage.newCopy(damage2)
    puts damage3.to_s
    puts damage.hasNoEffect

    damage4 = Deepspace::Damage.newCopy(damage2.adjust(armas, escudos))
    puts "----------"
    puts damage2.to_s
    puts damage4.to_s
    puts "----------"

    puts damage.nWeapons
    damage.discardWeapon(arma1)
    puts damage.nWeapons
    puts damage2.weapons
    damage2.discardWeapon(arma1)
    puts "\n"
    puts damage2.weapons
    puts damage.nShields
    damage.discardShieldBooster
    puts damage.nShields

    # prueba del adjust
    armas2 = Array.new()
    armas2.push(arma1.type)
    armas2.push(arma2.type)
    armas3 = Array.new()
    armas3.push(arma1.type)
    armas3.push(arma2.type)
    armas3.push(arma3.type)
    armas3.push(arma1.type)
    escudos2 = Array.new()
    escudos2.push(escudo)

    damage5 = Deepspace::Damage.newSpecificWeapons(armas2, 4)
    damage6 = Deepspace::Damage.newSpecificWeapons(armas3, 1)
    damage7 = Deepspace::Damage.newNumericWeapons(0, 0)
    damage8 = Deepspace::Damage.newNumericWeapons(0, 5)

    puts damage5.adjust(armas, escudos2).to_s
    puts damage6.adjust(armas, escudos2).to_s
    puts damage7.adjust(armas, escudos2).to_s
    puts damage8.adjust(armas, escudos2).to_s
    puts "\n"
    puts "\n"
    puts "----------------------------------------------------------------------------"
    puts "Probando clase Hangar"
    prueba = Deepspace::Hangar.new(4)
    puts prueba.maxElements
    puts prueba.shieldBoosters
    puts prueba.weapons
    puts prueba.spaceAvailable
    arma = Deepspace::Weapon.new('laser', Deepspace::WeaponType::PLASMA, 8)
    arma2 = Deepspace::Weapon.new('misil', Deepspace::WeaponType::MISSILE, 10)
    prueba.addWeapon(arma)
    prueba.addWeapon(arma2)
    escudo = Deepspace::ShieldBooster.new('prueba', 1, 1)
    prueba.addShieldBooster(escudo)
    prueba.addShieldBooster(escudo)
    puts prueba.weapons
    puts prueba.shieldBoosters
    puts prueba.spaceAvailable
    puts prueba.to_s
    prueba.removeWeapon(1)
    puts prueba.weapons
    prueba.removeShieldBooster(0)
    puts prueba.shieldBoosters
    prueba2 = Deepspace::Hangar.newCopy(prueba)
    puts "---------------------"
    puts prueba2.maxElements
    puts prueba2.weapons
    puts prueba2.shieldBoosters
    puts prueba2.spaceAvailable
    puts prueba2.to_s
    puts "\n"
    puts "\n"
    puts "----------------------------------------------------------------------------"
    puts "Probando clase SpaceStation"
    supplies = Deepspace::SuppliesPackage.new(1,2,3)
    prueba = Deepspace::SpaceStation.new("Prueba",supplies)
    puts prueba.name
    puts prueba.ammoPower
    puts prueba.fuelUnits
    puts prueba.nMedals
    puts prueba.shieldPower
    puts prueba.pendingDamage
    puts prueba.weapons
    puts prueba.shieldBoosters
    puts prueba.hangar
    puts prueba.to_s
    hangar = Deepspace::Hangar.new(4)
    weapon = Deepspace::Weapon.new('arma', Deepspace::WeaponType::PLASMA, 8)
    shield = Deepspace::ShieldBooster.new('escudo', 1, 1)
    puts prueba.receiveWeapon(weapon)
    puts prueba.receiveShieldBooster(shield)
    prueba.receiveHangar(hangar)
    puts prueba.receiveWeapon(weapon)
    puts prueba.receiveShieldBooster(shield)
    puts prueba.hangar
    prueba.mountWeapon(0)
    prueba.mountShieldBooster(0)
    puts prueba.hangar
    puts prueba.weapons
    puts prueba.shieldBoosters
    prueba.discardHangar
    puts prueba.hangar
    prueba.receiveHangar(hangar)
    puts prueba.receiveWeapon(weapon)
    puts prueba.receiveShieldBooster(shield)
    puts prueba.hangar
    prueba.discardWeaponInHangar(0)
    prueba.discardShieldBoosterInHangar(0)
    puts prueba.hangar
    puts prueba.getSpeed
    puts prueba.fuelUnits
    prueba.move
    puts prueba.fuelUnits
    weapon2 = Deepspace::Weapon.new('arma', Deepspace::WeaponType::PLASMA, 0)
    puts prueba.receiveWeapon(weapon2)
    prueba.mountWeapon(0)
    puts prueba.weapons
    puts "---"
    prueba.cleanUpMountedItems
    puts prueba.weapons
    puts prueba.validState
    damage = Deepspace::Damage.newNumericWeapons(2,2)
    prueba.setPendingDamage(damage)
    puts prueba.pendingDamage
    puts prueba.validState
    puts "\n"
    puts "\n"
    puts "----------------------------------------------------------------------------"
    puts "Probando clase EnemyStarShip"
    loot = Deepspace::Loot.new(1,2,3,4,5)
    damage = Deepspace::Damage.newNumericWeapons(2,2)
    prueba = Deepspace::EnemyStarShip.new("Prueba", 2.0, 3.0, loot, damage)
    puts prueba.name
    puts prueba.ammoPower
    puts prueba.shieldPower
    puts prueba.loot
    puts prueba.damage
    puts prueba.to_s
    puts prueba.protection
    puts prueba.fire
    puts prueba.receiveShot(4.0)


  end
end

prueba = TestP2.new()

puts prueba.main