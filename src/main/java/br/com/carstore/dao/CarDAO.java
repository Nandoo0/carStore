package br.com.carstore.dao;

import br.com.carstore.model.Car;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarDAO {

    public void createCar(Car car) {

        try {

            String SQL = "INSERT INTO CAR (NAME) VALUES (?)";

            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, car.getName());

            preparedStatement.execute();

            System.out.println("Sucesso ao gravar o carro no DB");

            connection.close();

        } catch (Exception e) {

            System.out.println("Erro ao gravar o carro no DB: " + e.getMessage());

        }

    }

    public List<Car> findAllCars() {

        try {

            String SQL = "SELECT * FROM CAR";

            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Car> cars = new ArrayList<>();

            while (resultSet.next()) {

                String carId = resultSet.getString("id");
                String carName = resultSet.getString("name");

                Car car = new Car(carId, carName);

                cars.add(car);

            }

            System.out.println("Sucesso ao consultar os carros no DB!");

            return cars;

        } catch (Exception e) {

            System.out.println("Erro ao consultar os carros no DB!");

        }

        return Collections.emptyList();

    }
    public void deleteCarById(String carId) {

        String SQL = "DELETE CAR WHERE ID = ?";

        try {

            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            System.out.println("success in database connection");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, carId);
            preparedStatement.execute();

            System.out.println("success on delete car with id: " + carId);

            connection.close();

        } catch (Exception e) {

            System.out.println("fail in database connection");

        }

    }

}
