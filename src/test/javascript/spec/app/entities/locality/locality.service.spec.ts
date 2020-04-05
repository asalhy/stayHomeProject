import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { LocalityService } from 'app/entities/locality/locality.service';
import { ILocality, Locality } from 'app/shared/model/locality.model';

describe('Service Tests', () => {
  describe('Locality Service', () => {
    let injector: TestBed;
    let service: LocalityService;
    let httpMock: HttpTestingController;
    let elemDefault: ILocality;
    let expectedResult: ILocality | ILocality[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(LocalityService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Locality(0, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should return a list of Locality', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            postalCode: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
