#encoding: utf-8
require_relative "LootToUI"
#Esta clase representa el botín que se obtiene al vencer a una nave enemiga. 

module Deepspace
	class Loot
		def initialize(supplies, weapons, shields, hangars, medals, ef = false , city = false)
			@nSupplies = supplies
			@nWeapons = weapons
			@nShields = shields
			@nHangars = hangars
			@nMedals = medals
			@getEfficient = ef
			@spaceCity = city
		end
		
			attr_reader :nSupplies , :nWeapons, :nShields, :nHangars , :nMedals, :getEfficient, :spaceCity  	

		def getUIversion
			return LootToUI.new(self)
		end
		def to_s
			getUIversion.to_s
		end
	end
end
