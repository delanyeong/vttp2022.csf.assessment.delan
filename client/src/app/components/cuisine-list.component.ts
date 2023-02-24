import { Component, OnInit } from '@angular/core';
import { RestaurantService } from '../restaurant-service';

@Component({
  selector: 'app-cuisine-list',
  templateUrl: './cuisine-list.component.html',
  styleUrls: ['./cuisine-list.component.css']
})
export class CuisineListComponent implements OnInit {
  
  // TODO Task 2
	// For View 1
  
  listOfCuisines : String [] = []

  constructor(private resSvc : RestaurantService) { }
  
  ngOnInit(): void {

    this.resSvc.getCuisineList()
      .then(result => {
        this.listOfCuisines = result
        console.info('result: ', result)
      })
      .catch(error => {
        console.error('>>> error: ', error)
      })

  }


}
