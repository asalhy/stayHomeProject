import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { GouvernoratService } from 'app/entities/gouvernorat/gouvernorat.service';
import { IGouvernorat, Gouvernorat } from 'app/shared/model/gouvernorat.model';

describe('Service Tests', () => {
  describe('Gouvernorat Service', () => {
    let injector: TestBed;
    let service: GouvernoratService;
    let httpMock: HttpTestingController;
    let elemDefault: IGouvernorat;
    let expectedResult: IGouvernorat | IGouvernorat[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(GouvernoratService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Gouvernorat(0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should return a list of Gouvernorat', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB'
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
