#encoding: utf-8
require_relative "DamageToUI"
module Deepspace
  class Damage
      @@NO_USE = -1
      def initialize(nw , ns, w)
        @nWeapons = nw
        @nShields = ns
        @weapons = w
      end

      def self.newNumericWeapons(w, s)
        new(w,s,nil)
      end

      #se inicializa con una lista de WeaponType
      def self.newSpecificWeapons(w, s)
        new(@@NO_USE,s,w)
      end

      def self.newCopy(d)
        new(d.nWeapons,d.nShields,d.weapons)
      end

      def getUIversion
        return DamageToUI.new(self)
      end

      private
      def arrayContainsType(w, t)
      encontrado = false
        pos = -1
        i = 0
        while i < w.length && !encontrado
          if (w.at(i).type == t)
            encontrado = true
            pos = i
          end
          i += 1
        end
        return pos
      end

      public
      def adjust(w, s)
        num_shields = [@nShields,s.length].min
        d = nil
        if @weapons == nil
          num_weapon = [@nWeapons,w.length].min
          d = Damage.newNumericWeapons(num_weapon,num_shields)
        else
          aux = w.clone
          waux = Array.new()
          @weapons.each do |n|
            index = aux.index(n)
            if index != nil
              waux.push(n)
              aux.delete_at(index)
            end
          end
          d = Damage.newSpecificWeapons(waux,num_shields)
        end
        return d
      end

      def discardWeapon(w)
        if (@nWeapons == @@NO_USE)
          if (@weapons.length != 0)
            @weapons.delete_if{ |a| a == w.type}
          end
        else
          if(@nWeapons>0)
            @nWeapons -= 1
          end
      end
      end

      def discardShieldBooster
        if(@nShields > 0)
          @nShields -= 1
        end
      end

      def hasNoEffect
        effect = false
        if @nWeapons == @@NO_USE
          effect = (@weapons.length + @nShields == 0)
        else
          effect = (@nWeapons + @nShields == 0)
        end
      end

      def nShields
        @nShields
      end

      def nWeapons
        @nWeapons
      end

      def weapons
        @weapons
      end

      def to_s
        getUIversion().to_s
      end


  end
end
