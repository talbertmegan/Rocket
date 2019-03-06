%%this file will find the gravity vector acting on an object at position

function g = findGravity(position, system)
    
    %find center of mass for a given system
    % TODO make a dynamic n-body sytem
    %for now, moon and earth are stationary and absolute
    
    %let bodies be a nx4 matrix containing the position and mass of each
    %body
    
    
    %gravity is sum of directional forces of gravity
    %where direction accelerations are GM/|r|^2 * rhat
    r(:,1:3) = system(:,1:3) - position(1:3);
      
    % a = GM/R^2
    
    
    
    acceleration(:) = 6.671e-11 * system(:,4) ./ vectorLength(r(:,1:3)).^2;
    gComponents(:, 1:3) = acceleration(:) .* unitVector(r(:,1:3));
    gComponents(find(isnan(gComponents))) = 0; %ignores objects inside the object being evaluated
    if length(gComponents(:,1)) ==1
        g = gComponents;
        return
    else
        g = sum(gComponents);
    end
    
    %g(1,1:3) = acceleration(:)*unitVector(r(:,1:3))
    %{
    the old method, but matlab sucks at loops so we switched to matrix
    for ii = 1:length(bodies(:,1))
        
        acceleration(ii,1) =(6.671e-11 * bodies(ii,4)) / vectorLength(r(ii,1:3))^2;
        g(1, 1:3) = g(1,1:3) + (acceleration(ii)* unitVector(r(ii,1:3)));
        
    end
    %}
    
end

function length = vectorLength(r)
    %there should be an easier way to do this with "sum(vector(:))" but it
    %wasnt working and frankly f- that
    length(:) = sqrt( r(:,1).^2 + r(:,2).^2 + r(:,3).^2);
    length = length';

end

function rhat = unitVector(r)
    rhat = r ./ vectorLength(r);
end