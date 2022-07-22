

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.generation.dishes.model.entities.Dish;
import com.generation.dishes.model.repositories.DishRepository;

@SpringBootTest
public class DishTest
{
	@Autowired
	DishRepository repository;
	@Test
	void dishReadTest() 
	{
		Dish dish =  repository.getById(1);
		//Dish dish = database.getDish(1);
		
		System.out.println("----------------------"+dish.getName()+"----------------------");
	}
}
