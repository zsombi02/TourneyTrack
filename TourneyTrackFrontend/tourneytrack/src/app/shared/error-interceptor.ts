import {HttpErrorResponse, HttpInterceptorFn} from '@angular/common/http';
import {catchError, throwError} from 'rxjs';

export const ErrorInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req).pipe(
    // Azonnali error alert, bármilyen hibánál
    // (Ha inkább snackbar, cseréld le!)
    catchError((error: HttpErrorResponse) => {
      if (error.error && error.error.message) {
        alert(error.error.message);
      } else {
        alert('Ismeretlen hiba történt.');
      }
      return throwError(() => error);
    })
  );
};
