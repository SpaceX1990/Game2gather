import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GameCollectionComponent } from './game-collection.component';

describe('GameCollectionComponent', () => {
  let component: GameCollectionComponent;
  let fixture: ComponentFixture<GameCollectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GameCollectionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GameCollectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});