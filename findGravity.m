%test stuff

findGravityTest([0,0,0])


function g = findGravityTest(position)
    
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
    r(:,[1:3]) = bodies(:,[1:3]) - position([1:3]);
    
    unitVector(r(1,[1:3]));
    
    
    for ii = 1:length(bodies(:,1))
        
        acceleration(ii,1) =(6.671e-11 * bodies(ii,4)) / vectorLength(r(ii,[1:3]))^2;
        g(ii, [1:3]) = acceleration(ii)* unitVector(r(ii,[1:3]));
        
    end
    
end

function length = vectorLength(r)
    length = sqrt( sum( r(:).^2));
end

function rhat = unitVector(r)
    rhat = r ./ vectorLength(r);
end