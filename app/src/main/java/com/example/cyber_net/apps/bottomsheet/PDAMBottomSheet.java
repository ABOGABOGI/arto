package com.example.cyber_net.apps.bottomsheet;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyber_net.apps.R;
import com.example.cyber_net.apps.model.inquiry.Inquiry;
import com.example.cyber_net.apps.model.payment.Payment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PDAMBottomSheet extends BottomSheetDialogFragment {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.ic_close)
    ImageView icClose;
    @BindView(R.id.btn_checkout)
    Button btnCheckout;
    @BindView(R.id.txt_nota)
    TextView txtNota;
    Unbinder unbinder;
    OnFnction listener;

    private static final String DESCRIBABLE_KEY = "describable_key";
    private Inquiry mDescribable;

   /* public static PDAMBottomSheet newInstance(Inquiry describable) {
        PDAMBottomSheet fragment = new PDAMBottomSheet();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DESCRIBABLE_KEY, describable);
        fragment.setArguments(bundle);

        return fragment;
    }*/

    public PDAMBottomSheet() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Making bottom sheet expanding to full height by default
       /* mDescribable = (Inquiry) getArguments().getSerializable(
                DESCRIBABLE_KEY);*/

        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                BottomSheetDialog d = (BottomSheetDialog) dialog;

                FrameLayout bottomSheet = d.findViewById(android.support.design.R.id.design_bottom_sheet);
                BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        //Toast.makeText(getActivity(), ""+mDescribable.getNama(), Toast.LENGTH_SHORT).show();
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ic_close, R.id.btn_checkout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ic_close:
                dismiss();
                break;
            case R.id.btn_checkout:
                listener.onClick();
                break;
        }
    }

    public interface OnFnction{
        void onClick();
    }
}
