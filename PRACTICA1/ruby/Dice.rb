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
			if @generator.rand() >= @NHANGARSPROB then
				result = 1
			end 
			return result
        
        end
        
        def initWithNWeapons
			valor = 1;
			prob = @generator.rand()
			if  prob <= @NWEAPONSPROB then
				valor = 1;
			elsif prob <= 2*@NWEAPONSPROB then
				valor = 2;
			elsif prob <= 1 then
				valor = 3;
			end
            
			return valor;
        
        end
        
        def initWithNShields
			result = 0
			if @generator.rand() >= @NSHIELDSPROB then
				result = 1
			end 
			return result
        
        end
        
        def whoStarts(nPlayers)
			@generator.rand(nPlayers)
        end 
        
        def firstShot 
			shot=0
			if @generator.rand() <= @FIRSTSHOTPROB then
				shot=GameCharacter::SPACESTATION
			else 
				shot=GameCharacter::ENEMYSTARSHIP
			end	
			
			return shot
        end
        
        def spaceStationMoves(speed)
			bool=false
			
			if @generator.rand() <= speed then
				bool=true
			end
			
			return bool
        end
        
        
	end
end 

