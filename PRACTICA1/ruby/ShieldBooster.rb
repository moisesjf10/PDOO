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


module 
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
			name
		end 
		
		def boost
			boost
		end 
		
		def uses
			uses
		end 
		
		def useIt
			aux = 0
			if @uses > @@MIN_USES then
				@uses -= 1
				aux = boost
			else 
				aux = @@DEFAULT_BOOST
			end 
			
			return aux
			
		end 
		
		
		
	end 
end 
