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

class TestP2
  def main
    puts "Probando clase Damage"
    damage=Deepspace::Damage.newNumericWeapons(15,20)
    puts damage.nShields
    puts damage.nWeapons
    puts damage.weapons
    puts damage.to_s

    arma1=Deepspace::Weapon.new("laser",Deepspace::WeaponType::LASER,8)
    arma2=Deepspace::Weapon.new("misil",Deepspace::WeaponType::MISSILE,10)
    arma3=Deepspace::Weapon.new("plasma",Deepspace::WeaponType::PLASMA,15)
    armas=Array.new(arma1,arma2,arma3)

    arma=Array.new(arma1.type,arma2.type)

    damage2=Deepspace::Damage.newSpecificWeapons(arma,10)
    escudo=Deepspace::ShieldBooster.new("name",2,2)
    escudos=Array.new(escudo)

    puts damage2.nShields
    puts damage2.nWeapons
    puts damage2.weapons

    damage3=Deepspace::Damage.newCopy(damage2)
    puts damage3.to_s
    puts damage.hasNoEffect

    damage4=Deepspace::Damage.newCopy(damage2.adjust(armas,escudos))
    puts "----------"
    puts damage2.to_s
    puts damage4.to_s
    puts "----------"

    puts damage.nWeapons
    damage.discardWeapon(arma1)
    puts damage.nWeapons
    puts damage2.weapons
    damage2.discardWeapon(arma1)
    puts damage2.weapons
    puts damage.nShields
    damage.discardShieldBooster
    puts damage.nShields

    #prueba del adjust
    armas2=Array.new(arma1.type,arma2.type)
    armas3=Array.new(arma1.type,arma2.type, arma3.type,arma1.type)
    escudos2=Array.new(escudo)

    damage5=Deepspace::Damage.newSpecificWeapons(armas2,4)
    damage6=Deepspace::Damage.newSpecificWeapons(armas3,1)
    damage7=Deepspace::Damage.newNumericWeapons(0,0)
    damage8=Deepspace::Damage.newNumericWeapons(0,5)

    puts damage5.adjust(armas,escudos2).to_s
    puts damage6.adjust(armas,escudos2).to_s
    puts damage7.adjust(armas,escudos2).to_s
    puts damage8.adjust(armas,escudos2).to_s


  end
end

prueba = TestP2.new()

puts prueba.main