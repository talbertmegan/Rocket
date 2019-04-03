function flag = collision(position, velocity, system)

    for ii = 1:length(system(:,1))
        
        radial_distance = sqrt( (position(1)-system(ii,1))^2 + (position(2)-system(ii,2))^2);
        
        if(radial_distance <= system(ii,8))
            
            %possible collision
            
            speed = sqrt( velocity(2)^2 + velocity(1)^2);
            
            %determine direction by unit vectorizing
            
            vhat = unitVector(velocity);
            rhat = unitVector( -1 * position(1:3) + system(ii,1:3));
            
            
            %fprintf("Vhat = < %f , %f, %f > \n rhat = < %f , %f, %f >\n", vhat, rhat);
           
            if rhat(1) < vhat(1) + 1 && rhat(1) > vhat(1) - 1 ...
                    && rhat(2) < vhat(2) + 1 && rhat(2) > vhat(2) - 1
                
                if speed > 100
                    flag = 1;
                else
                    flag = 2;
                end
                
            else
                flag = 3;
            end
            
            break;
            
            
        else
            flag = 0; %no collision
        end
    end

end

function length = vectorLength(r)
    %there should be an easier way to do this with "sum(vector(:))" but it
    %wasnt working and frankly this works
    length = sqrt( sum(r(:).^2));
    length = length';

end

function rhat = unitVector(r)
    rhat = r ./ vectorLength(r);
end