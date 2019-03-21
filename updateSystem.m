%%Updates the position and velocities of the bodies


function system = updateSystem(systemMatrix, dt)
    


    
    %TODO explain why this works in documentation
    for ii = 1:length(systemMatrix(:,1))
        systemMatrix(ii,[5:7]) = findVelocity( findGravity( systemMatrix(ii,[1:3]), systemMatrix ), systemMatrix(ii,5:7), dt);
        systemMatrix(ii, [1:3]) = findPosition( systemMatrix(ii,[5:7]), systemMatrix(ii,[1:3]), dt);
        
    end
    
    
    
    
    system = systemMatrix;
end
