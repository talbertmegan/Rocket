%%this file will find the gravity vector acting on an object at position

function g = findGravity(position, system)
    
    
    %let bodies be a nx4 matrix containing the position and mass of each
    %body
    
    
    %gravity is sum of directional forces of gravity
    %where direction accelerations are GM/|r|^2 * rhat
    r(:,1:3) = system(:,1:3) - position(1:3);
      
    % a = GM/R^2
    % g = sum(a)
    
    %putting it all in one mega line saves .002seconds, which is
    %significant given the importance of this function :/
    
    g = sum( (6.671e-11 * system(:,4) ./ vectorLength(r(:,1:3)).^2) .* unitVector(r(:,1:3)), 1, 'omitnan') ;
 
    %g = sum(acceleration, 1, 'omitnan')
    
    %{
    if length(gComponents(:,1)) ==1
        g = gComponents;
        return
    else
        g = sum(gComponents);
    end
    %}
    
    
    %g(1,1:3) = acceleration(:)*unitVector(r(:,1:3))
    %{
    the old method, but matlab slows at loops so we switched to matrix
    for ii = 1:length(bodies(:,1))
        
        acceleration(ii,1) =(6.671e-11 * bodies(ii,4)) / vectorLength(r(ii,1:3))^2;
        g(1, 1:3) = g(1,1:3) + (acceleration(ii)* unitVector(r(ii,1:3)));
        
    end
    %}
    
end

function length = vectorLength(r)
    %there should be an easier way to do this with "sum(vector(:))" but it
    %wasnt working and frankly this works
    length(:) = sqrt( r(:,1).^2 + r(:,2).^2 + r(:,3).^2);
    length = length';

end

function rhat = unitVector(r)
    rhat = r ./ vectorLength(r);
end