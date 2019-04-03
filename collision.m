function flag = collision(position, velocity, system)


    for ii = 1:length(system(:,1))
        
        radial_distance = sqrt( (position(1)-system(ii,1))^2 + (position(2)-system(ii,2))^2);
        
        if(radial_distance <= system(ii,8))
            
            %possible collision
            
            speed = sqrt( velocity(2)^2 + velocity(1)^2);
            
            
            
            % direction stuff is fucked up ?
            vel_direction = atan(velocity(2)/velocity(1));
            
            object_direction = -1 * atan( (position(2)-system(ii,2)) / (position(1)-system(ii,1)))
            
            if object_direction < vel_direction +0.1 && object_direction > vel_direction - 0.1
                
                %velocity in direction of object -- bad!
                if speed > 500
                    flag = 1 %dead
                else 
                    flag = 2 %good
                end
            else
                flag = 3; %flying away!
            end
                      
            %one collision is all that necessary
            break;
            
        else
            flag = 0; %no collision
        end
    end


end