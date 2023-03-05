#  Dive.rb
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

	class Dice
        def initialize 
			@NHANGARSPROB=0.25
			@NSHIELDSPROB=0.25
			@NWEAPONSPROB=0.33
			@FIRSTSHOTPROB=0.5
			@generator = Random.new()
        end 
        
        def initWithNHangars
			result = 0
			if @generator.rand() >= @NHANGARSPROB
				result = 1
			end 
			return result
        
        end
        
        def initWithNWeapons
			valor = 1;
			prob = @generator.rand()
			if  prob <= @NWEAPONSPROB
				valor = 1;
			elsif prob <= 2*@NWEAPONSPROB 
				valor = 2;
			elsif prob <= 1
				valor = 3;
			end
            
        return valor;
        
        end
        
        def initWithNShields
			result = 0
			if @generator.rand() >= @NSHIELDSPROB
				result = 1
			end 
			return result
        
        end
        
        def whoStarts(nPlayers)
        
        end 
        
        def firstShot 
        
        end
        
        def spaceStationMoves( speed)
        
        end
        
        
	end
end 

