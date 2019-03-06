%%Updates the position and velocities of the bodies
clear all
system = [...
        [0,-6.371e6,0],5.972e24, [-100,0,0];... %earth
        [0,383.4e6,0],7.3276e22, [0,0,0];... %moo
        [0,-383.4e6,0],7.3276e22, [0,0,0];... %moo
        
    ];
clear all
system = [...
        [0,0,0], 100, [0,0,0];...
        [0,10,0], 100, [10,0,0];...
        [0,-10,0], 100, [-10,0,0]...
        ];

for ii = 1:100
    recordPos1(ii,[1,2]) = system(1,[1,2]);
    recordVel1(ii, [1,2]) = system(1, 5:6);
    recordPos2(ii,1:2) = system(2,1:2)
    recordPos3(ii,1:2) = system(3,1:2);
    system = updateSystemTest(system,1);
    tIndex(ii) = ii;
    
end

figure
subplot(2,3,1)
plot( tIndex, recordPos1(:,1))
subplot(2,3,4)
plot( tIndex, recordPos1(:,2))
subplot(2,3,2)
plot( tIndex, recordPos2(:,1))
subplot(2,3,5)
plot( tIndex, recordPos2(:,2))
subplot(2,3,3)
plot( tIndex, recordPos3(:,1))
subplot(2,3,6)
plot( tIndex, recordPos3(:,2))

figure
subplot(1,3,1)
plot( recordPos1(:,1), recordPos1(:,2))
subplot(1,3,2)
plot( recordPos2(:,1), recordPos2(:,2))
subplot(1,3,3)
plot( recordPos3(:,1), recordPos3(:,2))

function system = updateSystemTest(systemMatrix, dt)
    
    
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
