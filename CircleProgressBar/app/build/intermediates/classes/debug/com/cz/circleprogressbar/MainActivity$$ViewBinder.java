// Generated code from Butter Knife. Do not modify!
package com.cz.circleprogressbar;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.cz.circleprogressbar.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492944, "field 'mToolbar'");
    target.mToolbar = finder.castView(view, 2131492944, "field 'mToolbar'");
    view = finder.findRequiredView(source, 2131492945, "field 'progressView'");
    target.progressView = finder.castView(view, 2131492945, "field 'progressView'");
  }

  @Override public void unbind(T target) {
    target.mToolbar = null;
    target.progressView = null;
  }
}
