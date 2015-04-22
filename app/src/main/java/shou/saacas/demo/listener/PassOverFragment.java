package shou.saacas.demo.listener;

import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jp.atrealize.saacas.ssc.Packet;
import jp.atrealize.saacas.ssc.SSCReceivedStatus;
import jp.atrealize.saacas.ssc.SaacasSSCListener;

public class PassOverFragment extends Fragment implements ConfirmDialogFragment.Callback {
    private static final String REQUEST_CODE_PASS_AMOUNT = "0001";

    private static final String REQUEST_CODE_QUERY = "0002";

    private static final String REQUEST_CODE_COMPLETED = "0004";

    private int amount;

    private boolean confirmed;

    public PassOverFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pass_over, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        SaacasSSCListener.getInstance().listen(new SaacasSSCListener.Callback() {
            @Override
            public Packet onPacketReceived(Packet packet, SSCReceivedStatus status, int sequence) {
                Logger.info("SSC packet received (request_code='%s', data='%s', response_code='%s', status='%s', sequence='%d').", packet.getRequestCode(), packet.getData(), packet.getResponseCode(), status, sequence);

                if (REQUEST_CODE_PASS_AMOUNT.equals(packet.getRequestCode())) {
                    String[] tokens = packet.getData().split(";");
                    amount = Integer.parseInt(tokens[0]);

                    FragmentManager manager = getFragmentManager();
                    ConfirmDialogFragment dialog = new ConfirmDialogFragment();
                    dialog.show(manager, "dialog");

                    return new Packet("0001", "", "0000");
                } else if (REQUEST_CODE_QUERY.equals(packet.getRequestCode())) {
                    if (confirmed) {
                        return new Packet("0003", "", "0000");
                    }
                    else {
                        return new Packet("0002", "", "0000");
                    }
                } else if (REQUEST_CODE_COMPLETED.equals(packet.getRequestCode())) {
                    return new Packet("0004", "", "0000");
                } else {
                    return new Packet("9999", "", "9999");
                }
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        SaacasSSCListener.getInstance().close();
    }

    @Override
    public void onConfirmed() {
        confirmed = true;
    }
}
