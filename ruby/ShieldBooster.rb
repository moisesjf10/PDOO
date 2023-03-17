#  ShieldBooster
#  
#  Copyright 2023 Manuel <manuel@manuel-ROG-Strix-G513IC-G513IC>
#  
#  This program is free software; you can redistribute it and/or modify
#  it under the terms of the GNU General Public License as published by
#  the Free Software Foundation; either version 2 of the License, or
#  (at your option) any later version.
#  
#  This program is distributed in the hope that it will be useful,
#  but WITHOUT ANY WARRANTY; without even the implied warranty of
#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#  GNU General Public License for more details.
#  
#  You should have received a copy of the GNU General Public License
#  along with this program; if not, write to the Free Software
#  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
#  MA 02110-1301, USA.
#  
#  


module Deepspace
	class ShieldBooster
		@DEFAULT_BOOST = 1.0
		@MIN_USES = 0
		
		def initialize (name,boost,uses)
			@name = name
			@boost = boost
			@uses = uses
		end 
		
		def self.newCopy(o)
			new(o.name,o.boost,o.uses)
		end
		
		def name
			@name
		end 
		
		def boost
			@boost
		end 
		
		def uses
			@uses
		end 
		
		def self.min_uses
			@MIN_USES
		end
			
		def self.default_boost
			@DEFAULT_BOOST
		end
		
		def useIt
			aux = 0
			if @uses > self.class.min_uses then
				@uses -= 1
				aux = boost
			else 
				aux = self.class.default_boost
			end 
			return aux
		end 		
	end
end
