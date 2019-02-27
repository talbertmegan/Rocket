%%this file will find the gravity vector acting on an object at position

function g = findGravity(position)
    
    %find center of mass for a given system
    % TODO make a dynamic n-body sytem
    %for now, moon and earth are stationary and absolute
    
    %let bodies be a nx4 matrix containing the position and mass of each
    %body
    bodies = [ ...
        [0,-6.371e6,0],5.972e24;... %earth
        [0,383.4e6,0],7.3276e22... %moon
        ];
    
    %gravity is sum of directional forces of gravity
    %where direction accelerations are GM/|r|^2 * rhat
    r(:,1:3) = bodies(:,1:3) - position(1:3);
    
    unitVector(r(1,1:3));
    
    %Pre-alloc space bc matlab constantly complains if you dont :(
    acceleration = zeros(length(bodies(:,1)),1);
    g = zeros(1, 3);
    
    for ii = 1:length(bodies(:,1))
        
        acceleration(ii,1) =(6.671e-11 * bodies(ii,4)) / vectorLength(r(ii,1:3))^2;
        g(1, 1:3) = g(1,1:3) + (acceleration(ii)* unitVector(r(ii,1:3)));
        
    end
    
end

function length = vectorLength(r)
    length = sqrt( sum( r(:).^2));
end

function rhat = unitVector(r)
    rhat = r ./ vectorLength(r);
end