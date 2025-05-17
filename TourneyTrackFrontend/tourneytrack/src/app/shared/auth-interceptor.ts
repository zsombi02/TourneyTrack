import { HttpInterceptorFn } from '@angular/common/http';

export const AuthInterceptor: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem('userToken');
  if (token) {
    const authReq = req.clone({
      headers: req.headers.set('Authorization', 'Basic ' + token)
    });
    return next(authReq);
  }
  return next(req);
};
