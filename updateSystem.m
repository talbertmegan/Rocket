%%Updates the position and velocities of the bodies


function system = updateSystem(systemMatrix, dt)
    
    
    %orbit in circles about the center of gravity
    %could try to find ellipticals but uhhh we'll throw that as a 
    %TODO if you do this in the future: better orbitals
    %center of gravity = sum( positions*mass)/total mass
    %removed, took my own advice
    %centerOfMass(1,1:3) = sum( systemMatrix(:,1:3).*systemMatrix(:,4))./sum(systemMatrix(:,4));
    
   
    for ii = 1:length(systemMatrix(:,1))
        systemMatrix(ii,[5:7]) = findVelocity( findGravity( systemMatrix(ii,[1:3]), systemMatrix ), systemMatrix(ii,5:7), dt);
        systemMatrix(ii, [1:3]) = findPosition( systemMatrix(ii,[5:7]), systemMatrix(ii,[1:3]), dt);
        
    end
    
    
    
    %update each body's velocity
    %findVelocity: aceleration, old vel, dt
    
    %systemMatrix(:, [5:7]) = findVelocity( findGravity( systemMatrix(:,[1:3]), systemMatrix ), systemMatrix(:,5:7), dt);
    %update position
    
    
   

    
   % findGravity( systemMatrix(:,1:3), systemMatrix-systemMatrix(:,:))
    
    
    
    system = systemMatrix;
end
